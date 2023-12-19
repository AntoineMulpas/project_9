import React, {useState} from 'react';
import {Button} from "react-bootstrap";
import Address from "../models/Address.jsx";
import Patient from "../models/Patient.jsx";
import axios from "axios";
import tokenStore from "../context/TokenStore.jsx";

function UpdatePatient ({patient, pop, id, close}) {
    const token = tokenStore(state => state.token)
    const [formData, setFormData] = useState({
        firstname: patient.firstName,
        lastname: patient.lastName,
        dob: patient.dob,
        address: patient.address.address,
        zip: patient.address.zip,
        city: patient.address.city,
        gender: patient.gender,
        phone: patient.phone,
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = () => {
        console.log(id)
        const address = new Address(null, formData.address, formData.zip, formData.city)
        const patient = new Patient(null, formData.firstname, formData.lastname, formData.dob, formData.phone, formData.gender, address)
        const url = "http://localhost:8765/api/v1/patient/update/" + id


        const headers = {
            'Authorization': 'Bearer ' + token,
            "content-type": "application/json; charset=UTF-8"
        }

        console.log(JSON.stringify(patient))
        axios.post(url,JSON.stringify(patient), {headers})
            .then(res => {
                console.log(res.data)
                pop()
                close()
            })
            .catch(res => {
                console.log(res.data)
                pop()
                close()
            })


    };

    return (
        <div className={"form-patient"}>
            <Button onClick={() => pop()}>Retour</Button>

            <h2>Informations personnelles</h2>
            <form>
                <label>
                    Prénom:
                    <input
                        type="text"
                        name="firstname"
                        value={formData.firstname}
                        onChange={handleInputChange}
                    />
                </label>
                <br />
                <label>
                    Nom:
                    <input
                        type="text"
                        name="lastname"
                        value={formData.lastname}
                        onChange={handleInputChange}
                    />
                </label>
                <br />
                <label>
                    Date de naissance:
                    <input
                        type="text"
                        name="dob"
                        placeholder={"Exemple 1992-03-29"}
                        value={formData.dob}
                        onChange={handleInputChange}
                    />
                </label>
                <br />
                <label>
                    Adresse:
                    <input
                        type="text"
                        name="address"
                        value={formData.address}
                        onChange={handleInputChange}
                    />
                </label>
                <br />
                <label>
                    Code postal:
                    <input
                        type="text"
                        name="zip"
                        value={formData.zip}
                        onChange={handleInputChange}
                    />
                </label>
                <br />
                <label>
                    Ville:
                    <input
                        type="text"
                        name="city"
                        value={formData.city}
                        onChange={handleInputChange}
                    />
                </label>
                <br />
                <label>
                    Sexe:
                    <select
                        name="gender"
                        value={formData.gender}
                        onChange={handleInputChange}
                    >
                        <option value="MALE">Male</option>
                        <option value="FEMALE">Female</option>
                    </select>
                </label>
                <br />
                <label>
                    Phone:
                    <input
                        type="tel"
                        name="phone"
                        value={formData.phone}
                        onChange={handleInputChange}
                    />
                </label>
                <br />
            </form>
            <Button type="submit" onClick={handleSubmit}>Enregistrer</Button>
        </div>
    );
}

export default UpdatePatient;