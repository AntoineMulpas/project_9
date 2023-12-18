import React, {useState} from 'react';
import {Button, Card} from "react-bootstrap";
import useNotes from "../hooks/useNotes.jsx";
import AddNote from "./AddNote.jsx";
import Modal from 'react-bootstrap/Modal';
import axios from "axios";
import useRisk from "../hooks/useRisk.jsx";
import tokenStore from "../context/TokenStore.jsx";

function PatientInformation ({patient, pop}) {
    const [refresh, setRefresh] = useState(false)
    const notes = useNotes(refresh ,patient.id)
    const risk = useRisk(refresh, patient.id, patient.gender, patient.dob)
    const [toggleAddNote, setToggleAddNote] = useState(false)
    const token = tokenStore(state => state.token)

    const handleDelete = (id) => {
        const url = "http://localhost:8765/api/v1/note/delete/" + id

        const headers = {
            'Authorization': 'Bearer ' + token,
            "content-type": "application/json; charset=UTF-8"
        }

        axios.get(url, {headers})
            .then(res => {
                console.log(res.data)
                setRefresh(!refresh)
            })
            .catch(res => {
                console.log(res.data)
            })
    }

    function PatientInformation () {
        return <>
            <Button onClick={pop}>Retour</Button>
            <Button onClick={() => setToggleAddNote(true)}>Ajouter une note</Button>
            <Card style={{width: '18rem'}}>
                <Card.Body>
                    <Card.Title>{patient.firstName} {patient.lastName}</Card.Title>
                    <Card.Text>
                        <strong>Risque: {risk.risk === null ? "aucun" : risk.risk}</strong>
                        <span>{patient.gender}</span>
                        <span>{patient.dob}</span>
                    </Card.Text>
                    <Card.Text>
                    <span>
                    {patient.address.address}
                </span>
                        <span>
                {patient.address.zip}
                </span>
                        <span>
                {patient.address.city}
                </span>
                        <span>{patient.phone}</span>
                    </Card.Text>
                </Card.Body>
            </Card>
            <h2>Notes concernant le patient</h2>
            {notes.map(note =>  <Modal.Dialog>
                <Modal.Header closeButton>
                    <Modal.Title>Note</Modal.Title>
                </Modal.Header>

                <Modal.Body>
                    <p>{note.note}</p>
                </Modal.Body>

                <Modal.Footer>
                    <Button variant="danger" onClick={() => handleDelete(note.id)}>Supprimer</Button>
                </Modal.Footer>
            </Modal.Dialog>)}
        </>;
    }

    return <div>
        {toggleAddNote ? <AddNote patientId={patient.id} pop={() => {
            setToggleAddNote(false)
            setRefresh(!refresh)
        }} /> :PatientInformation()}
    </div>
}

export default PatientInformation;