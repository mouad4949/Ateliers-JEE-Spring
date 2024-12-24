import React, { useState, useEffect } from 'react';
import api from '../utils/api';

const AbsenceForm = ({ absence, onSubmit, onCancel, etudiants }) => {
  const [dateAbsence, setDateAbsence] = useState(absence ? absence.dateAbsence : '');
  const [justification, setJustification] = useState(absence ? absence.justification : '');
  const [justified, setJustified] = useState(absence ? absence.justified : false);
  const [description, setDescription] = useState(absence ? absence.description : '');
  const [etudiantId, setEtudiantId] = useState(absence ? absence.etudiant?.id : '');
  const [formErrors, setFormErrors] = useState({});

  useEffect(() => {
    if (absence) {
      setDateAbsence(absence.dateAbsence);
      setJustification(absence.justification);
      setJustified(absence.justified);
      setDescription(absence.description);
      setEtudiantId(absence.etudiant?.id);
    }
  }, [absence]);
  const validateForm = () => {
      const errors = {};
      if (!dateAbsence) errors.dateAbsence = 'Date is required';
       if (!etudiantId) errors.etudiantId = 'Student is required';
      setFormErrors(errors);
      return Object.keys(errors).length === 0;
  };


  const handleSubmit = async (e) => {
    e.preventDefault();
      if (!validateForm()) {
          return;
      }
    const absenceData = {
        dateAbsence: dateAbsence,
        justification,
        justified,
        description,
        etudiant: { id: parseInt(etudiantId, 10)},
    };

    try {
      onSubmit(absenceData);

    } catch (error) {
        console.error("Erreur lors de la soumission du formulaire :", error)
        console.log(error);

    }
  };


  return (
    <form onSubmit={handleSubmit} className="max-w-md mx-auto p-4 bg-white shadow-md rounded-md mt-4">
      <div className="mb-4">
        <label htmlFor="dateAbsence" className="block text-gray-700 text-sm font-bold mb-2">Date d'absence:</label>
        <input
          type="date"
          id="dateAbsence"
          value={dateAbsence}
          onChange={(e) => setDateAbsence(e.target.value)}
          className={`shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline ${formErrors.dateAbsence ? 'border-red-500' : ''}`}
        />
           {formErrors.dateAbsence && <p className="text-red-500 text-xs italic">{formErrors.dateAbsence}</p>}
      </div>
      <div className="mb-4">
      <label htmlFor="etudiantId" className="block text-gray-700 text-sm font-bold mb-2">Étudiant:</label>
          <select id="etudiantId" value={etudiantId} onChange={(e) => setEtudiantId(e.target.value)}
              className={`shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline ${formErrors.etudiantId ? 'border-red-500' : ''}`}
        >
            <option value="">Choisir un étudiant</option>
            {etudiants.map(etudiant => (
                 <option key={etudiant.id} value={etudiant.id}>{etudiant.nom} {etudiant.prenom}</option>
            ))}
        </select>
          {formErrors.etudiantId && <p className="text-red-500 text-xs italic">{formErrors.etudiantId}</p>}
      </div>
      <div className="mb-4">
        <label htmlFor="justification" className="block text-gray-700 text-sm font-bold mb-2">Justification:</label>
        <input
          type="text"
          id="justification"
          value={justification}
          onChange={(e) => setJustification(e.target.value)}
          className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
        />
      </div>
      <div className="mb-4">
        <label htmlFor="justified" className="block text-gray-700 text-sm font-bold mb-2">Justified:</label>
        <input
          type="checkbox"
          id="justified"
          checked={justified}
          onChange={(e) => setJustified(e.target.checked)}
            className="ml-2"
        />
      </div>
      <div className="mb-4">
        <label htmlFor="description" className="block text-gray-700 text-sm font-bold mb-2">Description:</label>
        <textarea
          id="description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
        />
      </div>
        <div className="flex justify-end space-x-2">
            <button type="button" onClick={onCancel} className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Cancel</button>
            <button type="submit" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
              {absence ? 'Update' : 'Add'}
            </button>
        </div>
    </form>
  );
};

export default AbsenceForm;