import React from 'react';
import { NavLink } from 'react-router-dom';


const Navigation = () => {
    return (
        <nav className="bg-gray-800 p-4">
            <div className="container mx-auto flex justify-between items-center">
              <div className="flex items-center">
               
                <span className="text-white text-xl font-bold">Gestion d'absences</span>
              </div>
                <ul className="flex space-x-4">
                    <li>
                        <NavLink to="/" className="text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md font-medium" exact>Home</NavLink>
                    </li>
                    <li>
                        <NavLink to="/absences" className="text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md font-medium">Absences</NavLink>
                    </li>
                    <li>
                        <NavLink to="/etudiants" className="text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md font-medium">Étudiants</NavLink>
                    </li>
                </ul>
            </div>
        </nav>
    );
};

export default Navigation;