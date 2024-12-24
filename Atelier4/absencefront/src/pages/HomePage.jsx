import React from 'react';

const HomePage = () => {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-100">
        <div className="bg-white p-8 rounded-md shadow-md text-center">
          <h1 className="text-3xl font-bold mb-4 text-gray-800">
            Bienvenue sur l'application de gestion d'absences
          </h1>
          <p className="text-gray-600">
            Naviguez à travers les onglets pour gérer les étudiants et leurs
            absences.
          </p>
        </div>
      </div>
    );
  };

export default HomePage;