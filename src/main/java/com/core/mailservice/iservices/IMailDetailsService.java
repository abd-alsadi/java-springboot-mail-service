package com.core.mailservice.iservices;

import com.core.mailservice.models.AuthunticationModel;
import com.core.mailservice.models.MailDetailsModel;

import java.util.*;

public interface IMailDetailsService {

    public String SendMail(AuthunticationModel auth,MailDetailsModel object);
    
    public List<MailDetailsModel> GetAll(AuthunticationModel auth);  

    public MailDetailsModel GetByID(AuthunticationModel auth,UUID id);

    public MailDetailsModel Add(AuthunticationModel auth,MailDetailsModel object);

    public MailDetailsModel Delete(AuthunticationModel auth,UUID id);

    public MailDetailsModel Update(AuthunticationModel auth,UUID id,MailDetailsModel object);
}
