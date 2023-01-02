package com.example.dsproject.services;

import com.example.dsproject.dtos.DeviceDto;
import com.example.dsproject.dtos.MeasurementDto;
import com.example.dsproject.dtos.builders.MeasurementBuilder;
import com.example.dsproject.repositories.MeasurementRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<MeasurementDto> findMeasurementByDate(String date) throws ParseException {
        return measurementRepository.findAllByMeasurementDate(new SimpleDateFormat("yyyy-MM-dd").parse(date)).stream().map(MeasurementBuilder::toMeasurementDto).collect(Collectors.toList());
    }
}
