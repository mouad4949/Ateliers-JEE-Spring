import React, { useState, useEffect } from 'react';
import EtudiantForm from '../components/EtudiantForm';
import EtudiantList from '../components/EtudiantList';
import LoadingSpinner from '../components/LoadingSpinner';
import api from '../utils/api';

const EtudiantsPage = () => {
    const [etudiants, setEtudiants] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [showForm, setShowForm] = useState(false);
    const [currentEtudiant, setCurrentEtudiant] = useState(null);
    const [searchKeyword, setSearchKeyword] = useState('');


    useEffect(() => {
        fetchEtudiants();
    }, []);

    const fetchEtudiants = async () => {
        setLoading(true);
        try {
             let response;
            if(searchKeyword) {
                 response = await api.get(`/etudiants/search?keyword=${searchKeyword}`);
            } else {
                 response = await api.get('/etudiants');

            }
            setEtudiants(response.data);
            setLoading(false);
        } catch (err) {
             setError(err.message)
             setLoading(false);
        }
    };

    const handleAddEtudiant = () => {
        setCurrentEtudiant(null);
        setShowForm(true)
    };

    const handleCreateEtudiant = async (etudiantData) => {
        setLoading(true);
        try {
            await api.post('/etudiants', etudiantData);
             fetchEtudiants();
            setShowForm(false)
            setLoading(false);
        } catch (err) {
            setError(err.message);
             setLoading(false);
        }
    };


    const handleUpdateEtudiant = async (etudiantData) => {
      setLoading(true);
        try {
            await api.put(`/etudiants/${currentEtudiant.id}`, etudiantData);
            fetchEtudiants();
             setShowForm(false);
            setCurrentEtudiant(null);
            setLoading(false);
        } catch (err) {
            setError(err.message);
            setLoading(false);
        }

    };

    const handleSubmit = async (etudiantData) => {
      if (currentEtudiant) {
          await handleUpdateEtudiant(etudiantData);
      } else {
          await handleCreateEtudiant(etudiantData);
      }

    };
    const handleEditEtudiant = async (id) => {
        setLoading(true);
        try {
            const response = await api.get(`/etudiants/${id}`);
            setCurrentEtudiant(response.data);
            setShowForm(true);
            setLoading(false);
        } catch (err) {
            setError(err.message);
             setLoading(false);
        }

    };
    const handleDeleteEtudiant = async (id) => {
      setLoading(true);
        try {
            await api.delete(`/etudiants/${id}`);
            fetchEtudiants();
             setLoading(false);
        } catch (err) {
             setError(err.message);
              setLoading(false);
        }

    };

    const handleCancelForm = () => {
        setShowForm(false);
         setCurrentEtudiant(null);
    };

    const handleSearch = async (keyword) => {
         setSearchKeyword(keyword);
         fetchEtudiants();
    };

    if (loading) {
        return <LoadingSpinner />;
    }
    if (error) {
        return <div className="text-center mt-4 text-red-500">Error : {error}</div>
    }
    return (
      <div className="container mx-auto p-4">
        <h2 className="text-2xl font-bold mb-4">Gestion des Étudiants</h2>
         <div className="flex justify-end mb-4">
          <button onClick={handleAddEtudiant} className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Add Etudiant</button>
        </div>

         {showForm && (
          <EtudiantForm
           etudiant={currentEtudiant}
           onSubmit={handleSubmit}
           onCancel={handleCancelForm}
          />
        )}

        <EtudiantList etudiants={etudiants} onDelete={handleDeleteEtudiant} onEdit={handleEditEtudiant} onSearch={handleSearch} />
      </div>
    );
};

export default EtudiantsPage;