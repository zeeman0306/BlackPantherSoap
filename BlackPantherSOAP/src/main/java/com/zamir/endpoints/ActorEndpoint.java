package com.zamir.endpoints;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.zamir.actor_ws.AddActorRequest;
import com.zamir.actor_ws.AddActorResponse;
import com.zamir.actor_ws.ActorInfo;
import com.zamir.actor_ws.DeleteActorRequest;
import com.zamir.actor_ws.DeleteActorResponse;
import com.zamir.actor_ws.GetAllActorsResponse;
import com.zamir.actor_ws.GetActorByIdRequest;
import com.zamir.actor_ws.GetActorByIdResponse;
import com.zamir.actor_ws.ServiceStatus;
import com.zamir.actor_ws.UpdateActorRequest;
import com.zamir.actor_ws.UpdateActorResponse;
import com.zamir.entity.Actor;
import com.zamir.service.IActorService;

@Endpoint
public class ActorEndpoint {
	private static final String NAMESPACE_URI = "http://www.zamir.com/actor-ws";
	@Autowired
	private IActorService actorService;	

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getActorByIdRequest")
	@ResponsePayload
	public GetActorByIdResponse getActor(@RequestPayload GetActorByIdRequest request) {
		GetActorByIdResponse response = new GetActorByIdResponse();
		ActorInfo actorInfo = new ActorInfo();
		BeanUtils.copyProperties(actorService.getActorById(request.getActorId()), actorInfo);
		response.setActorInfo(actorInfo);
		return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllActorsRequest")
	@ResponsePayload
	public GetAllActorsResponse getAllActors() {
		GetAllActorsResponse response = new GetAllActorsResponse();
		List<ActorInfo> actorInfoList = new ArrayList<>();
		List<Actor> actorList = actorService.getAllActors();
		for (int i = 0; i < actorList.size(); i++) {
		     ActorInfo ob = new ActorInfo();
		     BeanUtils.copyProperties(actorList.get(i), ob);
		     actorInfoList.add(ob);    
		}
		response.getActorInfo().addAll(actorInfoList);
		return response;
	}	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addActorRequest")
	@ResponsePayload
	public AddActorResponse addActor(@RequestPayload AddActorRequest request) {
		AddActorResponse response = new AddActorResponse();		
    	        ServiceStatus serviceStatus = new ServiceStatus();		
		Actor actor = new Actor();
		actor.setName(request.getName());
		actor.setDescription(request.getDescription());		
                boolean flag = actorService.addActor(actor);
                if (flag == false) {
        	   serviceStatus.setStatusCode("CONFLICT");
        	   serviceStatus.setMessage("Content Already Available");
        	   response.setServiceStatus(serviceStatus);
                } else {
		   ActorInfo actorInfo = new ActorInfo();
	           BeanUtils.copyProperties(actor, actorInfo);
		   response.setActorInfo(actorInfo);
        	   serviceStatus.setStatusCode("SUCCESS");
        	   serviceStatus.setMessage("Content Added Successfully");
        	   response.setServiceStatus(serviceStatus);
                }
                return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateActorRequest")
	@ResponsePayload
	public UpdateActorResponse updateActor(@RequestPayload UpdateActorRequest request) {
		Actor actor = new Actor();
		BeanUtils.copyProperties(request.getActorInfo(), actor);
		actorService.updateActor(actor);
    	        ServiceStatus serviceStatus = new ServiceStatus();
    	        serviceStatus.setStatusCode("SUCCESS");
    	        serviceStatus.setMessage("Content Updated Successfully");
    	        UpdateActorResponse response = new UpdateActorResponse();
    	        response.setServiceStatus(serviceStatus);
    	        return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteActorRequest")
	@ResponsePayload
	public DeleteActorResponse deleteActor(@RequestPayload DeleteActorRequest request) {
		Actor actor = actorService.getActorById(request.getActorId());
    	        ServiceStatus serviceStatus = new ServiceStatus();
		if (actor == null ) {
	    	    serviceStatus.setStatusCode("FAIL");
	    	    serviceStatus.setMessage("Content Not Available");
		} else {
		    actorService.deleteActor(actor);
	    	    serviceStatus.setStatusCode("SUCCESS");
	    	    serviceStatus.setMessage("Content Deleted Successfully");
		}
    	        DeleteActorResponse response = new DeleteActorResponse();
    	        response.setServiceStatus(serviceStatus);
		return response;
	}	
} 