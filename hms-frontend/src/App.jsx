import { BrowserRouter, Routes, Route } from "react-router-dom";

import LoginPage from "./pages/LoginPage";
import DoctorsPage from "./pages/DoctorsPage";
import PatientsPage from "./pages/PatientsPage";
import AppointmentsPage from "./pages/AppointmentsPage";
import DashboardPage from "./pages/DashboardPage";
import ProtectedRoute from "./routes/ProtectedRoute";

function App() {

    return (

        <BrowserRouter>

            <Routes>

                <Route path="/" element={<LoginPage />} />

                <Route
                    path="/doctors"
                    element={
                        <ProtectedRoute>
                            <DoctorsPage />
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/patients"
                    element={
                        <ProtectedRoute>
                            <PatientsPage />
                        </ProtectedRoute>
                    }

                />

                <Route
                    path="/appointments"
                    element={
                        <ProtectedRoute>
                            <AppointmentsPage />
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/dashboard"
                    element={
                        <ProtectedRoute>
                            <DashboardPage />
                        </ProtectedRoute>
                    }
                />

            </Routes>

        </BrowserRouter>
    );
}

export default App;