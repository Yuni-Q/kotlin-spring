package com.yuniq.yuniq

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User(
    var userId: String,
    var password: String,
    @Id @GeneratedValue var id:Long?=null
)

//CREATE TABE User (
//    userId TEXT NOT NULL,
//    password VARCHAR(20) NOT NULL,
//    id LONG NOT NULL PRIME KEY
//)
