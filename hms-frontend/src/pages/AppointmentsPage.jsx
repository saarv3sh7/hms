import { useEffect, useState } from "react";
import API from "../api/axios";
import Navbar from "../components/Navbar";

function AppointmentsPage() {

    const [appointments, setAppointments] = useState([]);

    const [formData, setFormData] = useState({
        patientId: "",
        doctorId: "",
        appointmentTime: ""
    });

    // Fetch appointments
    const fetchAppointments = async () => {

        try {

            const response = await API.get("/appointments");

            setAppointments(response.data);

        } catch (error) {

            console.log(error);

            alert("Failed to fetch appointments");
        }
    };

    // Create appointment
    const createAppointment = async () => {

        try {

            await API.post("/appointments", formData);

            alert("Appointment created successfully");

            fetchAppointments();

            setFormData({
                patientId: "",
                doctorId: "",
                appointmentTime: ""
            });

        } catch (error) {

            console.log(error);

            alert("Failed to create appointment");
        }
    };

    // Update status
    const updateStatus = async (id, status) => {

        try {

            await API.put(`/appointments/${id}`, {
                status
            });

            fetchAppointments();

        } catch (error) {

            console.log(error);

            alert("Failed to update status");
        }
    };

    // Delete appointment
    const deleteAppointment = async (id) => {

        try {

            await API.delete(`/appointments/${id}`);

            alert("Appointment deleted");

            fetchAppointments();

        } catch (error) {

            console.log(error);

            alert("Failed to delete appointment");
        }
    };

    useEffect(() => {

        fetchAppointments();

    }, []);

    return (
        <>
            <Navbar />
        <div className="min-h-screen bg-gray-100 p-8">

            <h1 className="text-3xl font-bold mb-6">
                Appointment Management
            </h1>

            {/* Create Appointment Form */}

            <div className="bg-white p-6 rounded-2xl shadow-lg mb-8">

                <h2 className="text-xl font-bold mb-4">
                    Create Appointment
                </h2>

                <div className="grid grid-cols-3 gap-4">

                    <input
                        type="number"
                        placeholder="Patient ID"
                        className="border border-gray-300 p-3 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400"
                        value={formData.patientId}
                        onChange={(e) =>
                            setFormData({
                                ...formData,
                                patientId: e.target.value
                            })
                        }
                    />

                    <input
                        type="number"
                        placeholder="Doctor ID"
                        className="border border-gray-300 p-3 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400"
                        value={formData.doctorId}
                        onChange={(e) =>
                            setFormData({
                                ...formData,
                                doctorId: e.target.value
                            })
                        }
                    />

                    <input
                        type="datetime-local"
                        className="border border-gray-300 p-3 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400"
                        value={formData.appointmentTime}
                        onChange={(e) =>
                            setFormData({
                                ...formData,
                                appointmentTime: e.target.value
                            })
                        }
                    />

                </div>

                <button
                    onClick={createAppointment}
                    className="mt-4 bg-blue-500 text-white px-4 py-2 rounded"
                >
                    Create Appointment
                </button>

            </div>

            {/* Appointments Table */}

            <div className="bg-white p-6 rounded shadow">

                <h2 className="text-xl font-bold mb-4">
                    All Appointments
                </h2>

                <table className="w-full border text-center">

                    <thead>

                    <tr className="bg-gray-200">

                        <th className="p-2 border">ID</th>
                        <th className="p-2 border">Patient</th>
                        <th className="p-2 border">Doctor</th>
                        <th className="p-2 border">Time</th>
                        <th className="p-2 border">Status</th>
                        <th className="p-2 border">Actions</th>

                    </tr>

                    </thead>

                    <tbody>

                    {appointments.map((appointment) => (

                        <tr key={appointment.id}
                            className="hover:bg-gray-100 transition"
                        >

                            <td className="p-2 border">
                                {appointment.id}
                            </td>

                            <td className="p-2 border">
                                {appointment.patientName}
                            </td>

                            <td className="p-2 border">
                                {appointment.doctorName}
                            </td>

                            <td className="p-2 border">
                                {appointment.appointmentTime}
                            </td>

                            <td className="p-2 border">
                                {appointment.status}
                            </td>

                            <td className="p-2 gap-2 flex justify-center border">

                                <button
                                    onClick={() =>
                                        updateStatus(
                                            appointment.id,
                                            "COMPLETED"
                                        )
                                    }
                                    className="bg-green-500 text-white px-2 py-1 rounded"
                                >
                                    Complete
                                </button>

                                <button
                                    onClick={() =>
                                        updateStatus(
                                            appointment.id,
                                            "CANCELLED"
                                        )
                                    }
                                    className="bg-yellow-500 text-white px-2 py-1 rounded"
                                >
                                    Cancel
                                </button>

                                <button
                                    onClick={() =>
                                        deleteAppointment(appointment.id)
                                    }
                                    className="bg-red-500 text-white px-2 py-1 rounded"
                                >
                                    Delete
                                </button>

                            </td>

                        </tr>

                    ))}

                    </tbody>

                </table>

            </div>

        </div>
            </>
    );
}

export default AppointmentsPage;