/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Pedro Arthur
 * Created: 09/02/2017
 */

CREATE TABLE contact (
    
    id SERIAL,
    firstname TEXT,
    lastname TEXT,
    email TEXT,
    phone TEXT,
    PRIMARY KEY(id)

);