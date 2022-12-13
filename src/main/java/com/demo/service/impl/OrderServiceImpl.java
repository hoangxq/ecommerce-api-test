package com.demo.service.impl;

import com.demo.model.*;
import com.demo.repository.*;
import com.demo.service.OrderService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.request.OrderRequest;
import com.demo.web.dto.response.*;
import com.demo.web.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final AccountRepository accountRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final MappingHelper mappingHelper;

    @Override
    public List<OrderResponse> getAllOrderOfUser(Long userId) {
        return orderRepository.findAllByAccount_Id(userId).stream()
                .map(e -> {
                    var orderResponse = modelMapper.map(e, OrderResponse.class);
                    orderResponse.setAccountResponse(modelMapper
                            .map(accountRepository.findById(userId)
                                            .orElseThrow(
                                                    () -> new EntityNotFoundException(Account.class.getName(), userId.toString())),
                                    AccountResponse.class));
                    orderResponse.setOrderItemResponses(e.getOrderItems().stream()
                            .map(item -> modelMapper.map(item, OrderItemResponse.class)).collect(Collectors.toSet()));
                    return orderResponse;
                }).collect(Collectors.toList());
    }

    @Override
    public OrderResponse createOrder(Long userId, OrderRequest orderRequest) {
        if (orderRequest.getCartItemIds().size() <= 0) {
            log.error("Nothing cart item is choose");
            return null;
        }
        var order = orderRepository.save(modelMapper.map(orderRequest, Order.class));
        var orderItems = orderRequest.getCartItemIds().stream()
                .map(e -> {
                    var cartItem = cartItemRepository.findById(e).orElseThrow(
                            () -> new EntityNotFoundException(CartItem.class.getName(), e.toString())
                    );
                    var orderItem = modelMapper.map(cartItem, OrderItem.class);
                    orderItem.setOrder(order);
                    return orderItem;
                }).collect(Collectors.toSet());
        orderItemRepository.saveAll(orderItems);
        order.setTotalPrice(orderItems.stream()
                .map(e -> e.getProduct().getPrice() * e.getQuantity())
                .reduce(0.0, Double::sum));
        order.setAccount(accountRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException(Account.class.getName(), userId.toString())
        ));
        order.setStatus(OrderStatusConstants.PENDING);
        order.setOrderItems(orderItems);
        return modelMapper.map(orderRepository.save(order), OrderResponse.class);
    }

    @Override
    public OrderResponse updateOrder(Long orderId, OrderRequest source) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(Order.class.getName(), orderId.toString()));
        source.setStatus(source.getStatus().equalsIgnoreCase(OrderStatusConstants.CANCELED) &&
                order.getStatus().equalsIgnoreCase(OrderStatusConstants.PENDING) ?
                OrderStatusConstants.CANCELED : order.getStatus());
        mappingHelper.mapIfSourceNotNullAndStringNotBlank(source, order);
        return modelMapper.map(orderRepository.save(order), OrderResponse.class);
    }

    @Override
    public List<OrderItemResponse> getOrderItemOfOrder(Long orderId) {
        return orderItemRepository.findByOrder_Id(orderId).stream()
                .map(e -> {
                    var item = modelMapper.map(e, OrderItemResponse.class);
                    item.setProductResponse(modelMapper.map(e.getProduct(), ProductResponse.class));
                    item.getProductResponse()
                            .setCategoryRes(e.getProduct().getCategories().stream()
                                    .map(i -> i.getName()).collect(Collectors.toSet()));
                    item.setTotalPrice(e.getQuantity()*e.getProduct().getPrice());
                    return item;
                }).collect(Collectors.toList());
    }
}
