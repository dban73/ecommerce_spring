package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.entity.ConfirmationToken;
import com.posgrado.ecommerce.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

  private ConfirmationTokenRepository repository;

  @Override
  public ConfirmationToken save(ConfirmationToken confirmationToken) {
    return repository.save(confirmationToken);
  }

}
