import React, {useEffect, useState} from 'react';
import axios from "axios";
import tokenStore from "../context/TokenStore.jsx";

function useNotes (refresh, id) {
    const [data, setData] = useState([])
    const token = tokenStore(state => state.token)


    useEffect(() => {
        const url = "http://localhost:8765/api/v1/note/patient/" + id


        const headers = {
            'Authorization': 'Bearer ' + token,
        }

        axios.get(url, {headers})
            .then(res => {
                console.log(res.data)
                setData(res.data)
            })
    }, [refresh])

    return data
}

export default useNotes;