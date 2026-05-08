import { Link, useNavigate } from "react-router-dom";

function Navbar() {

    const navigate = useNavigate();

    const logout = () => {

        localStorage.removeItem("token");

        navigate("/");
    };

    return (

        <nav className="bg-gray-900 text-white px-8 py-4 flex justify-between items-center shadow-md">

            {/* Logo */}

            <h1 className="text-2xl font-bold tracking-wide">
                HMS
            </h1>

            {/* Navigation Links */}

            <div className="flex gap-6 items-center">

                <Link
                    to="/dashboard"
                    className="hover:text-gray-300 transition"
                >
                    Dashboard
                </Link>

                <Link
                    to="/doctors"
                    className="hover:text-gray-300 transition"
                >
                    Doctors
                </Link>

                <Link
                    to="/patients"
                    className="hover:text-gray-300 transition"
                >
                    Patients
                </Link>

                <Link
                    to="/appointments"
                    className="hover:text-gray-300 transition"
                >
                    Appointments
                </Link>

                <button
                    onClick={logout}
                    className="bg-red-500 hover:bg-red-600 px-4 py-2 rounded-lg transition"
                >
                    Logout
                </button>

            </div>

        </nav>
    );
}

export default Navbar;