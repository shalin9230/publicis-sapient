package com.assignment.booking_service.service;

import com.assignment.booking_service.entiry.Booking;
import com.assignment.booking_service.exception.ShowtimeNotFoundException;
import com.assignment.booking_service.repository.BookingRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class BookingService {
    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private ShowtimeClient showtimeClient;

    private RazorpayClient client;

    private static final String SECRET_ID = "rzp_test_4Q9Z4gFWd7z4y1";
    private static final String SECRET_KEY = "J40vjxMkdY132zHsLmI5ECfa";

    public Booking bookTickets(Long userId, Long showtimeId, Integer seats){
        if (!showtimeClient.doesShowtimeExist(showtimeId)) {
            throw new ShowtimeNotFoundException("Invalid showtimeId: Showtime does not exist");
        }

        showtimeClient.bookSeats(showtimeId, seats);
        String bookingAmount = String.valueOf(150 * seats);
        /*Order order = null;
        try {
            order = createRazorPayOrder( bookingAmount);
        } catch (RazorpayException e) {
            log.error(e.getMessage());
        }*/
        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setShowtimeId(showtimeId);
        booking.setSeatsBooked(seats);
        /*if(order != null) {
            booking.setPaymentId(order.get("id"));
            booking.setPaymentStatus(order.get("status"));
        }*/
        booking.setPaymentId(String.valueOf(UUID.randomUUID()));
        booking.setPaymentStatus("PAID");
        return bookingRepo.save(booking);
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepo.findByUserId(userId);
    }

    public List<Booking> getBookingByShowtimeId(Long showtimeId){
        return bookingRepo.findByShowtimeId(showtimeId);
    }

    private Order createRazorPayOrder(String amount) throws RazorpayException {

        JSONObject options = new JSONObject();
        options.put("amount", convertRupeeToPaise(amount));
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");
        options.put("payment_capture", 1);
        return client.Orders.create(options);
    }

    private String convertRupeeToPaise(String paise) {
        BigDecimal b = new BigDecimal(paise);
        BigDecimal value = b.multiply(new BigDecimal("100"));
        return value.setScale(0, RoundingMode.UP).toString();

    }

}
