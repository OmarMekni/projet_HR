package com.example.projet_hr.exeption;

/**
 * Classe NotFoundException qui gère les NotFindException pour les 3 model
 */
public class NotFoundException extends RuntimeException
{
    public NotFoundException(String message) {
        super(message);
    }
}
