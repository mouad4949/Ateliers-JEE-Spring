import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import HomePage from './pages/HomePage';
import AbsencesPage from './pages/AbsencesPage';
import EtudiantsPage from './pages/EtudiantsPage';
import NotFoundPage from './pages/NotFoundPage';
import Navigation from "./components/Navigation";

function App() {
    return (
        <Router>
            <Navigation/>
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/absences" element={<AbsencesPage />} />
                <Route path="/etudiants" element={<EtudiantsPage />} />
                <Route path="/not-found" element={<NotFoundPage />} />
                <Route path="*" element={<Navigate to="/not-found" />} />
            </Routes>
        </Router>
    );
}

export default App;