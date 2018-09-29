package com.restws;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.restws.exceptions.PatientBusinessException;
import com.restws.model.Patient;

@Service // Spring annotation. Spring will scan through classes & determine this is a Service class.
public class PatientServiceImpl implements PatientService {

    long currentId = 123;
    Map<Long, Patient> patients = new HashMap<>();

    public PatientServiceImpl() {
        init();
    }

    private void init() {

        Patient patient = new Patient(); // initialize a db table with one record.
        patient.setId(currentId);
        patient.setName("John");
        patients.put(patient.getId(), patient);
    }

    public List<Patient> getPatients() {

        Collection<Patient> results = patients.values();
        List<Patient> response = new ArrayList<>(results);
        return response;
    }

    public Patient getPatient(Long id) {

        System.out.println("...invoking getPatient, Patient Id is... " + id);
        if (patients.get(id) == null) {
            throw new NotFoundException();
        }
        return patients.get(id);
    }

    public Response createPatient(Patient patient) {

        System.out.println("...invoking createPatient, Patient Name is... " + patient.getName());
        patient.setId(++currentId);
        patients.put(patient.getId(), patient);

        // Return a response with a 200 HTTP status. Our patient object will be serialized client
        // Invoke.build so the response will build.
        return Response.ok(patient).build();
    }

    public Response updatePatient(Patient patient) {

        System.out.println("...invoking updatePatient, Patient Name is... " + patient.getName());

        // Make sure patient exists in map so get the patient and check if null.
        Patient currentPatient = patients.get(patient.getId());

        Response response;
        if (currentPatient != null) {
            // if patient exists then update the patient.
            patients.put(patient.getId(), patient);
            response = Response.ok().build();
        } else {
            //response = Response.notModified().build();
            throw new PatientBusinessException();
        }

        return response;
    }

    public Response deletePatient(Long id) {

        System.out.println("...invoking deletePatients, Patient Id is... " + id);
        Patient patient = patients.get(id);

        Response response;

        if (patient != null) {
            patients.remove(id);
            response = Response.ok().build();
        } else {
            response = Response.notModified().build();
        }

        return response;
    }
}
