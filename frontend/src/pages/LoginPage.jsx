import React, {useState} from 'react';
import axios from "axios";
import {useNavigate} from "react-router-dom";
import tokenStore from "../context/TokenStore.jsx";

function LoginPage () {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate()
    const updateToken = tokenStore(state => state.updateToken)

    const handleLogin = () => {
        const json = {
            "username": username,
            "password": password
        }

        const headers = {
            'Content-Type': 'application/json',
        }

        const url = "http://localhost:8765/api/v1/authentication/auth"
        axios.post(url, JSON.stringify(json), headers)
            .then(res => {
                updateToken(res.data)
                console.log(res.data)
                navigate("/patients")
            })

    };

    return (
        <div>
            <h2>Medilabo</h2>
            <form>
                <label>
                    Username:
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                </label>
                <br />
                <label>
                    Password:
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </label>
                <br />
                <button type="button" onClick={handleLogin}>
                    Login
                </button>
            </form>
        </div>
    );
}

export default LoginPage;