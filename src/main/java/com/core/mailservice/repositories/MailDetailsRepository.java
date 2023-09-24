package com.core.mailservice.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import com.core.mailservice.models.*;
import java.util.*;


@Repository
@Transactional
public interface MailDetailsRepository extends JpaRepository<MailDetailsModel,UUID>{
    
}