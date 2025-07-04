package com.zosh.configrations;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class OracleSequenceGenerator {

    private final JdbcTemplate jdbcTemplate;

    public OracleSequenceGenerator(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long getNextSequenceValue(String sequenceName) {
        String sql = "SELECT " + sequenceName + ".NEXTVAL FROM DUAL";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    // Métodos específicos para cada secuencia
    public Long getNextBookingId() {
        return getNextSequenceValue("bookings_seq");
    }

    public Long getNextUserId() {
        return getNextSequenceValue("users_seq");
    }

    public Long getNextSalonId() {
        return getNextSequenceValue("salons_seq");
    }

    public Long getNextCategoryId() {
        return getNextSequenceValue("categories_seq");
    }

    public Long getNextServiceId() {
        return getNextSequenceValue("service_offerings_seq");
    }

    public Long getNextNotificationId() {
        return getNextSequenceValue("notifications_seq");
    }

    public Long getNextPaymentOrderId() {
        return getNextSequenceValue("payment_orders_seq");
    }
}