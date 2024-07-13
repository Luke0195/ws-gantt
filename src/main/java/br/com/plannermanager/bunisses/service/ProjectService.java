package br.com.plannermanager.bunisses.service;

import br.com.plannermanager.dto.request.ProjectRequest;
import br.com.plannermanager.dto.response.ProjectPayload;

public interface ProjectService {

    ProjectPayload create(ProjectRequest projectRequest);
}
