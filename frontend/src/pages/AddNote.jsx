import React, {useState} from 'react';
import {Button} from "react-bootstrap";
import Note from "../models/Note.jsx";
import axios from "axios";
import tokenStore from "../context/TokenStore.jsx";

function AddNote ({patientId, pop}) {
    const [note, setNote] = useState(null)
    const token = tokenStore(state => state.token)


    const handleSubmit = () => {
        const noteToSave = new Note(null, note, patientId)

        const url = "http://localhost:8765/api/v1/note/add"


        const headers = {
            'Authorization': 'Bearer ' + token,
            "content-type": "application/json; charset=UTF-8"
        }

        console.log(JSON.stringify(noteToSave))
        axios.post(url,JSON.stringify(noteToSave), {headers})
            .then(res => {
                console.log(res.data)
                pop()
            })
            .catch(res => {
                console.log(res.data)
                pop()
            })

    }

    return (
        <div>
            <h1>Ajouter une note</h1>
            <input type={"text"} onChange={event => setNote(event.target.value)}/>
            <Button onClick={handleSubmit}>Ajouter</Button>
        </div>
    );
}

export default AddNote;