import React from 'react';

const NotFoundPage = () => {
    return (
        <div className="flex justify-center items-center h-screen">
            <div className="text-center">
                <h1 className="text-4xl font-bold text-gray-800 mb-4">404</h1>
                <p className="text-lg text-gray-600">Page Not Found</p>
            </div>
        </div>
    );
};

export default NotFoundPage;