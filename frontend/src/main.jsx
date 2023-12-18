import React, {useMemo} from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import {createHashRouter, RouterProvider} from "react-router-dom";
import LoginPage from "./pages/LoginPage.jsx";
import PatientPage from "./pages/PatientPage.jsx";



const router = createHashRouter([
    {
        path: "/",
        element: <LoginPage />,
    },
    {
        path: "/patients",
        element: <PatientPage />
    }
]);
ReactDOM.createRoot(document.getElementById('root')).render(
    <RouterProvider router={router} />
)
