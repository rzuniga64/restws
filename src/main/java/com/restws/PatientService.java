package com.restws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.restws.model.Patient;

@Consumes("application/xml,application/json")
@Produces("application/xml,application/json")
@Path("/patientservice") // Used by the client to access the web service.
public interface PatientService {

    /**
     *  Get list of all patients.
     *  @return a List of all Patients.
     */
    @GET
    @Path("/patients")
    List<Patient> getPatients();

    /**
     *  Get a single patient given his ID.
     * @param id the patient's unique id.
     * @return  the Patient with the given id.
     */
    @GET
    @Path("/patients/{id}/") // {id} is a path variable, bind to method parameter using @PathParam
    Patient getPatient(@PathParam("id") Long id);

    /**
     *  Create a patient and add it to the patient collection that comes in the request.
     *  @param patient the patient to add.
     *  @return a response with a 200 HTTP status.
     */
    @POST
    @Path("/patients/")
    Response createPatient(Patient patient);

    /**
     *  Update a patient, given the patient name, if it exists in the patient collection.
     *  @param patient the patient to update.
     *  @return a response with a 200 HTTP status if patient updated. Otherwise throws a PatientBusinessException.
     */
    @PUT
    @Path("/patients/")
    Response updatePatient(Patient patient);

    /**
     *  Delete a patient, given the patient id, if it exists in the patient collection.
     *  @param id the patient to delete.
     *  @return a response with a 200 HTTP status if patient updated. Otherwise it returns a unmodified response.
     */
    @DELETE
    @Path("/patients/{id}/")
    Response deletePatient(@PathParam("id") Long id);
}