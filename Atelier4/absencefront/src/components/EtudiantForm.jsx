import React, { useState, useEffect } from 'react';

const EtudiantForm = ({ etudiant, onSubmit, onCancel }) => {
    const [nom, setNom] = useState(etudiant ? etudiant.nom : '');
    const [prenom, setPrenom] = useState(etudiant ? etudiant.prenom : '');
    const [email, setEmail] = useState(etudiant ? etudiant.email : '');
    const [classe, setClasse] = useState(etudiant ? etudiant.classe : '');
    const [dateNaissance, setDateNaissance] = useState(etudiant ? etudiant.dateNaissance : '');
    const [formErrors, setFormErrors] = useState({});

    useEffect(() => {
        if (etudiant) {
            setNom(etudiant.nom);
            setPrenom(etudiant.prenom);
            setEmail(etudiant.email);
            setClasse(etudiant.classe);
            setDateNaissance(etudiant.dateNaissance);
        }
    }, [etudiant]);
    const validateForm = () => {
        const errors = {};
        if (!nom) errors.nom = 'Name is required';
        if (!prenom) errors.prenom = 'Last Name is required';
        if (!email) errors.email = 'Email is required';
        if (!classe) errors.classe = 'Class is required';
        if (!dateNaissance) errors.dateNaissance = 'Birth date is required';

        setFormErrors(errors);
        return Object.keys(errors).length === 0;
    };
    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!validateForm()) {
            return;
        }
        const etudiantData = { nom, prenom, email, classe, dateNaissance };
        try{
            onSubmit(etudiantData)
        }catch (error){
            console.error("Erreur lors de la soumission du formulaire :", error)
            console.log(error);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="max-w-md mx-auto p-4 bg-white shadow-md rounded-md mt-4">
            <div className="mb-4">
                <label htmlFor="nom" className="block text-gray-700 text-sm font-bold mb-2">Nom:</label>
                <input
                    type="text"
                    id="nom"
                    value={nom}
                    onChange={(e) => setNom(e.target.value)}
                    className={`shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline ${formErrors.nom ? 'border-red-500' : ''}`}

                />
                {formErrors.nom && <p className="text-red-500 text-xs italic">{formErrors.nom}</p>}
            </div>
            <div className="mb-4">
                <label htmlFor="prenom" className="block text-gray-700 text-sm font-bold mb-2">Prénom:</label>
                <input
                    type="text"
                    id="prenom"
                    value={prenom}
                    onChange={(e) => setPrenom(e.target.value)}
                    className={`shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline ${formErrors.prenom ? 'border-red-500' : ''}`}

                />
                {formErrors.prenom && <p className="text-red-500 text-xs italic">{formErrors.prenom}</p>}

            </div>
            <div className="mb-4">
                <label htmlFor="email" className="block text-gray-700 text-sm font-bold mb-2">Email:</label>
                <input
                    type="email"
                    id="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    className={`shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline ${formErrors.email ? 'border-red-500' : ''}`}
                />
                {formErrors.email && <p className="text-red-500 text-xs italic">{formErrors.email}</p>}

            </div>
            <div className="mb-4">
                <label htmlFor="classe" className="block text-gray-700 text-sm font-bold mb-2">Classe:</label>
                <input
                    type="text"
                    id="classe"
                    value={classe}
                    onChange={(e) => setClasse(e.target.value)}
                    className={`shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline ${formErrors.classe ? 'border-red-500' : ''}`}

                />
                {formErrors.classe && <p className="text-red-500 text-xs italic">{formErrors.classe}</p>}

            </div>
             <div className="mb-4">
                <label htmlFor="dateNaissance" className="block text-gray-700 text-sm font-bold mb-2">Date de Naissance:</label>
                <input
                    type="date"
                    id="dateNaissance"
                    value={dateNaissance}
                    onChange={(e) => setDateNaissance(e.target.value)}
                    className={`shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline ${formErrors.dateNaissance ? 'border-red-500' : ''}`}

                />
                 {formErrors.dateNaissance && <p className="text-red-500 text-xs italic">{formErrors.dateNaissance}</p>}

            </div>
            <div className="flex justify-end space-x-2">
                <button type="button" onClick={onCancel} className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Cancel</button>
                <button type="submit" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                    {etudiant ? 'Update' : 'Add'}
                </button>
            </div>
        </form>
    );
};

export default EtudiantForm;