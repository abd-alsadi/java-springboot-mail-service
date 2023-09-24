package com.core.mailservice.models;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "mail_details")
public class MailDetailsModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String TableName="mail_details";

    @Id
    @Column(name = "flduid")
    private UUID  id;

    @Column(name = "fldtoken")
    private String  token;

    @Column(name = "fldclientid")
    private String clientID;

    @Column(name = "fldsource")
    private String  source;

    @Column(name = "fldusername")
    private String userName;

    @Column(name = "fldsessionid")
    private String sessionID;

    @Column(name = "fldfrom")
    private String from;

    @Column(name = "fldrecipient")
    private String recipient;

    @Column(name = "fldcc")
    private String cc;


    @Column(name = "fldbcc")
    private String bcc;


    @Column(name = "fldsentDate")
    private String sentDate;

    @Column(name = "fldreplayTo")
    private String replayTo;

    @Column(name = "fldmsgBody")
    private String msgBody;

    @Column(name = "fldsubject")
    private String subject;

    @Column(name = "fldattachment")
    private String attachment;

    @Column(name = "fldstatus")
    private String  status;

    @Column(name = "fldflag")
    private int flag;

    @Column(name = "fldcreatedat")
    private String createdAt;

    @Column(name = "fldupdatedat")
    private String updatedAt;

}
