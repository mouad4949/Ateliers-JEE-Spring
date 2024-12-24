import React, { useState, useEffect } from 'react';
import AbsenceForm from '../components/AbsenceForm';
import AbsenceList from '../components/AbsenceList';
import LoadingSpinner from '../components/LoadingSpinner';
import api from '../utils/api';


const AbsencesPage = () => {
    const [absences, setAbsences] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [showForm, setShowForm] = useState(false);
    const [currentAbsence, setCurrentAbsence] = useState(null);
    const [etudiants, setEtudiants] = useState([]);

    useEffect(() => {
        fetchEtudiants();
        fetchAbsences();
    }, []);


    const fetchAbsences = async () => {
      setLoading(true);
        try {
            const response = await api.get('/absences');
            setAbsences(response.data);
            setLoading(false);
        } catch (err) {
            setError(err.message)
            setLoading(false);
        }

    };

    const fetchEtudiants = async () => {
        setLoading(true);
        try {
            const response = await api.get('/etudiants');
            setEtudiants(response.data);
            setLoading(false);

        } catch (err) {
            setError(err.message)
             setLoading(false);
        }

    };

    const handleAddAbsence = () => {
        setCurrentAbsence(null);
        setShowForm(true)
    };


    const handleCreateAbsence = async (absenceData) => {
        try {
            setLoading(true);
            await api.post('/absences', absenceData);
            fetchAbsences();
            setShowForm(false);
             setLoading(false);
        } catch (err) {
            setError(err.message);
             setLoading(false);
        }

    };

    const handleUpdateAbsence = async (absenceData) => {
        try {
            setLoading(true);
            await api.put(`/absences/${currentAbsence.id}`, absenceData);
            fetchAbsences();
            setShowForm(false);
            setCurrentAbsence(null);
             setLoading(false);
        } catch (err) {
            setError(err.message);
             setLoading(false);
        }

    };
    const handleSubmit = async (absenceData) => {
      if (currentAbsence) {
          await handleUpdateAbsence(absenceData);
      }else {
          await handleCreateAbsence(absenceData);
      }
    };


    const handleEditAbsence = async (id) => {
        setLoading(true);
        try {
            const response = await api.get(`/absences/${id}`);
             setCurrentAbsence(response.data);
            setShowForm(true);
             setLoading(false);
        } catch (err) {
            setError(err.message);
             setLoading(false);
        }

    };


    const handleDeleteAbsence = async (id) => {
        setLoading(true);
        try {
             await api.delete(`/absences/${id}`);
             fetchAbsences();
            setLoading(false);
        } catch (err) {
            setError(err.message);
             setLoading(false);
        }

    };
    const handleCancelForm = () => {
        setShowForm(false);
         setCurrentAbsence(null);
    };
    if (loading) {
        return <LoadingSpinner />;
    }
    if (error) {
        return <div className="text-center mt-4 text-red-500">Error : {error}</div>
    }

    return (
        <div className="container mx-auto p-4">
            <h2 className="text-2xl font-bold mb-4">Gestion des Absences</h2>
            <div className="flex justify-end mb-4">
                <button onClick={handleAddAbsence} className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Add Absence</button>
            </div>
            {showForm && (
                <AbsenceForm
                    absence={currentAbsence}
                    onSubmit={handleSubmit}
                    onCancel={handleCancelForm}
                    etudiants={etudiants}
                />
            )}

            <AbsenceList absences={absences} onDelete={handleDeleteAbsence} onEdit={handleEditAbsence} />
        </div>
    );
};

export default AbsencesPage;