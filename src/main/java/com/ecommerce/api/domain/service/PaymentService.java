package com.ecommerce.api.domain.service;

import com.ecommerce.api.api.dto.PaymentPreferenceDTO;
import com.ecommerce.api.exception.MyException;
import com.ecommerce.api.utils.MessageException;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.AddressRequest;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.common.PhoneRequest;
import com.mercadopago.client.preference.*;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    @Value("${mercado-pago.access-token}")
    private String ACCESS_TOKEN;

    @Value("${back-url.success}")
    private String BACK_URL_SUCCESS;
    @Value("${back-url.failure}")
    private String BACK_URL_FAILURE;
    @Value("${back-url.pending}")
    private String BACK_URL_PENDING;

    public String createPreference(PaymentPreferenceDTO paymentPreferenceDTO) {

        // Configura o token de acesso (Está em produção)
        MercadoPagoConfig.setAccessToken(this.ACCESS_TOKEN);

        // Cria o item de preferência
        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                .id(paymentPreferenceDTO.getId())
                .title(paymentPreferenceDTO.getTitle())
                .currencyId("BRL")
                .pictureUrl(paymentPreferenceDTO.getPictureUrl())
                .description(paymentPreferenceDTO.getDescription())
                .categoryId(paymentPreferenceDTO.getCategoryId())
                .quantity(paymentPreferenceDTO.getQuantity())
                .unitPrice(paymentPreferenceDTO.getUnitPrice())
                .build();

        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);

        // Cria o pagador (payer)
        PreferencePayerRequest payerRequest = PreferencePayerRequest.builder()
                .name(paymentPreferenceDTO.getName())
                .surname(paymentPreferenceDTO.getSurname())
                .email(paymentPreferenceDTO.getEmail())
                .phone(PhoneRequest.builder()
                        .areaCode(paymentPreferenceDTO.getPhoneAreaCode())
                        .number(paymentPreferenceDTO.getPhoneNumber())
                        .build())
                .identification(IdentificationRequest.builder()
                        .type("CPF")
                        .number(paymentPreferenceDTO.getCpf())
                        .build())
                .address(AddressRequest.builder()
                        .streetName(paymentPreferenceDTO.getStreetName())
                        .streetNumber(paymentPreferenceDTO.getStreetNumber())
                        .zipCode(paymentPreferenceDTO.getZipCode())
                        .build())
                .build();

        // Configura URLs de retorno
        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success(this.BACK_URL_SUCCESS)
                .failure(this.BACK_URL_FAILURE)
                .pending(this.BACK_URL_PENDING)
                .build();


        // Cria a requisição de preferência
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .payer(payerRequest)
                .backUrls(backUrls)
                .autoReturn("approved")
                .notificationUrl("https://www.your-site.com/ipn")
                .statementDescriptor("MEUNEGOCIO")
                .externalReference("Reference_1234")
                .expires(true)
                .expirationDateFrom(OffsetDateTime.now())
                .expirationDateTo(OffsetDateTime.now())
                .build();

        try {
            // Envia a requisição para criar a preferência
            Preference preference = new PreferenceClient().create(preferenceRequest);
            return preference.getId();
        } catch (Exception e) {
            throw new MyException(MessageException.MSG_0001);
        }
    }
}
