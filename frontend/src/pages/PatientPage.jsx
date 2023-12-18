import React, {useState} from 'react';
import usePatients from "../hooks/usePatients.jsx";
import {Button, Card} from "react-bootstrap";
import PatientInformation from "./PatientInformation.jsx";
import axios from "axios";
import AddPatient from "./AddPatient.jsx";
import tokenStore from "../context/TokenStore.jsx";

function PatientPage () {
    const [refresh, setRefresh] = useState(false)
    const patients = usePatients(refresh);
    const [toggleInformation, setToggleInformation] = useState(false)
    const [toggleAddPatient, setToggleAddPatient] = useState(false)
    const [patientInfo, setPatientInfo] = useState(null)
    const token = tokenStore(state => state.token)


    const handleDelete = (id) => {
        const url = "http://localhost:8765/api/v1/patient/delete/" + id


        const headers = {
            'Authorization': 'Bearer ' + token,
        }

        axios.get(url, {headers})
            .then(res => {
                setRefresh(!refresh)
            })

    }

    function PatientsList () {
        return <>
            <div className={"patients-title-container"}>
                <h1>Mes patients</h1>
                <Button onClick={() => setToggleAddPatient(true)}>Ajouter</Button>

            </div>
            <div className={"patient-cards-container"}>
                {patients.map(patient => <Card style={{width: '18rem'}}>
                    <Card.Body>
                        <Card.Title>{patient.firstName} {patient.lastName}</Card.Title>
                        <Card.Text>
                            {patient.dob}
                        </Card.Text>
                        <Button variant="primary" onClick={() => {
                            setPatientInfo(patient)
                            setToggleInformation(true)
                        }}>Voir</Button>
                        <Button onClick={() => handleDelete(patient.id)} variant={"danger"}>Supprimer</Button>
                    </Card.Body>
                </Card>)}
            </div>
        </>;
    }

    function Patients () {
        return <>
            {toggleInformation ?
             <PatientInformation patient={patientInfo} pop={() => {
                 setToggleInformation(false)
                 setPatientInfo(null)
             }
             }/> :
             <PatientsList/>}
        </>;
    }

    return (
        <div>
            {toggleAddPatient ? <AddPatient pop={() => {
                setToggleAddPatient(false)
                setRefresh(!refresh)
            }} /> : Patients()}
        </div>
    );
}

export default PatientPage;