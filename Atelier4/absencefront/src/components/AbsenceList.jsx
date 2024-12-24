import React from 'react';

const AbsenceList = ({ absences, onDelete, onEdit }) => {
    return (
      <div className="overflow-x-auto mt-4">
        <table className="min-w-full leading-normal">
          <thead>
            <tr className="bg-gray-200 text-gray-600 uppercase text-sm leading-normal">
              <th className="py-3 px-6 text-left">ID</th>
              <th className="py-3 px-6 text-left">Date Absence</th>
              <th className="py-3 px-6 text-left">Justification</th>
              <th className="py-3 px-6 text-left">Justified</th>
              <th className="py-3 px-6 text-left">Description</th>
                <th className="py-3 px-6 text-left">Étudiant</th>
              <th className="py-3 px-6 text-left">Actions</th>
            </tr>
          </thead>
          <tbody className="text-gray-600 text-sm font-light">
            {absences && absences.map((absence) => (
              <tr key={absence.id} className="border-b border-gray-200 hover:bg-gray-100">
                <td className="py-3 px-6 text-left whitespace-nowrap">
                  {absence.id}
                </td>
                <td className="py-3 px-6 text-left whitespace-nowrap">
                  {absence.dateAbsence}
                </td>
                <td className="py-3 px-6 text-left">
                  {absence.justification}
                </td>
                  <td className="py-3 px-6 text-left">
                      {absence.justified ? 'Yes' : 'No'}
                  </td>
                <td className="py-3 px-6 text-left">
                  {absence.description}
                </td>
                  <td className="py-3 px-6 text-left">
                     {/* Conditional rendering here */}
                     {absence.etudiant ? `${absence.etudiant.nom} ${absence.etudiant.prenom}` : 'N/A'}
                </td>
                <td className="py-3 px-6 text-left">
                    <button
                      onClick={() => onEdit(absence.id)}
                      className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline mr-2"
                    >
                      Edit
                    </button>
                    <button
                      onClick={() => onDelete(absence.id)}
                      className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    >
                      Delete
                    </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
          {!absences || absences.length === 0 ? (
              <p className="text-center mt-4">Aucune absence trouvée.</p>
          ) : null}
      </div>
    );
};

export default AbsenceList;