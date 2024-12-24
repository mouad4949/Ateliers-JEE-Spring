import React from 'react';

const EtudiantList = ({ etudiants, onDelete, onEdit , onSearch}) => {

    const handleSearch = (e) => {
      onSearch(e.target.value);
    };

    return (
      <div className="overflow-x-auto mt-4">
        <div className="mb-4 flex justify-end">
          <input type="text"
                placeholder="Search by name"
                className="shadow appearance-none border rounded w-1/4 py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                onChange={handleSearch}
            />
        </div>
        <table className="min-w-full leading-normal">
          <thead>
            <tr className="bg-gray-200 text-gray-600 uppercase text-sm leading-normal">
              <th className="py-3 px-6 text-left">ID</th>
              <th className="py-3 px-6 text-left">Nom</th>
              <th className="py-3 px-6 text-left">Prénom</th>
              <th className="py-3 px-6 text-left">Email</th>
              <th className="py-3 px-6 text-left">Classe</th>
                <th className="py-3 px-6 text-left">Date de Naissance</th>

              <th className="py-3 px-6 text-left">Actions</th>
            </tr>
          </thead>
          <tbody className="text-gray-600 text-sm font-light">
            {etudiants && etudiants.map((etudiant) => (
              <tr key={etudiant.id} className="border-b border-gray-200 hover:bg-gray-100">
                <td className="py-3 px-6 text-left whitespace-nowrap">
                  {etudiant.id}
                </td>
                <td className="py-3 px-6 text-left whitespace-nowrap">
                  {etudiant.nom}
                </td>
                <td className="py-3 px-6 text-left">
                  {etudiant.prenom}
                </td>
                  <td className="py-3 px-6 text-left">
                    {etudiant.email}
                </td>
                  <td className="py-3 px-6 text-left">
                    {etudiant.classe}
                </td>
                <td className="py-3 px-6 text-left">
                    {etudiant.dateNaissance}
                </td>
                <td className="py-3 px-6 text-left">
                  <button
                    onClick={() => onEdit(etudiant.id)}
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline mr-2"
                  >
                    Edit
                  </button>
                  <button
                    onClick={() => onDelete(etudiant.id)}
                    className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
          {!etudiants || etudiants.length === 0 ? (
              <p className="text-center mt-4">Aucun étudiant trouvé.</p>
          ) : null}
      </div>
    );
};

export default EtudiantList;