import React, {useState} from 'react';
import Address from "../models/Address.jsx";
import Patient from "../models/Patient.jsx";
import axios from "axios";
import tokenStore from "../context/TokenStore.jsx";

function AddPatient ({pop}) {
    const token = tokenStore(state => state.token)
    const [formData, setFormData] = useState({
        firstname: '',
        lastname: '',
        dob: '',
        address: '',
        zip: '',
        city: '',
        gender: 'MALE',
        phone: '',
    });

    // Function to handle form field changes
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = () => {
        const address = new Address(null, formData.address, formData.zip, formData.city)
        const patient = new Patient(null, formData.firstname, formData.lastname, formData.dob, formData.phone, formData.gender, address)
        const url = "http://localhost:8765/api/v1/patient/add"


        const headers = {
            'Authorization': 'Bearer ' + token,
            "content-type": "application/json; charset=UTF-8"
        }

        console.log(JSON.stringify(patient))
        axios.post(url,JSON.stringify(patient), {headers})
            .then(res => {
                console.log(res.data)
                pop()
            })
            .catch(res => {
                console.log(res.data)
                pop()
            })


    };

    return (
        <div>
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
            <button type="submit" onClick={handleSubmit}>Enregistrer</button>
        </div>
    );
}

export default AddPatient;