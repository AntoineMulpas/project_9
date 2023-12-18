import React from 'react';

function Patient (id, firstName, lastName, dob, phone, gender, address) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = dob;
    this.phone = phone;
    this.gender = gender;
    this.address= address;
}

export default Patient;