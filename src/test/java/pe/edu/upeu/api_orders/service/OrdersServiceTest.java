package pe.edu.upeu.api_orders.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upeu.api_orders.model.Order;
import pe.edu.upeu.api_orders.repository.OrderRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrdersServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void cuandoMontoEsMenorOIgualA1000_entoncesNoAplicaDescuento() {
        // GIVEN: Monto de 500 (No supera el límite de 1000)
        Order orderInput = new Order();
        orderInput.setAmount(500.0);

        when(repository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN: Se ejecuta la lógica del servicio
        Order resultado = orderService.createOrder(orderInput);

        // THEN: Verificamos que el monto no cambió
        assertNotNull(resultado);
        assertEquals(500.0, resultado.getAmount());
        verify(repository, times(1)).save(orderInput);
    }

    @Test
    void cuandoMontoEsMayorA1000_entoncesAplicaDiezPorCientoDescuento() {
        // GIVEN: Monto de 2000 (Supera los 1000)
        Order orderInput = new Order();
        orderInput.setAmount(2000.0);

        when(repository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN: Se ejecuta la lógica del servicio
        Order resultado = orderService.createOrder(orderInput);

        // THEN: Verificamos el descuento del 10% (2000 * 0.9 = 1800)
        assertNotNull(resultado);
        assertEquals(1800.0, resultado.getAmount());
        verify(repository, times(1)).save(any(Order.class));
    }

    @Test
    void cuandoSeListanLasOrdenes_entoncesRetornaLaListaCompleta() {
        // GIVEN: Simulamos datos en la BD
        Order o1 = new Order();
        Order o2 = new Order();
        when(repository.findAll()).thenReturn(Arrays.asList(o1, o2));

        // WHEN: Llamamos al método
        List<Order> resultado = orderService.getAllOrders();

        // THEN: Validamos el tamaño de la lista
        assertEquals(2, resultado.size());
        verify(repository, times(1)).findAll();
    }
}