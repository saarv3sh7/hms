import { useState } from "react";
import API from "../api/axios";
import { useNavigate } from "react-router-dom";
import bgImage from "../assets/bg2.jpg";

function LoginPage() {

    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = async () => {

        try {

            const response = await API.post("/auth/login", {
                email,
                password
            });

            localStorage.setItem("token", response.data.token);

            alert("Login Successful");

            navigate("/dashboard");

        } catch (error) {

            alert("Invalid Credentials");
        }
    };

    return (
        <div
            className="flex justify-center items-center h-screen bg-cover bg-center"
            style={{
                backgroundImage: `url(${bgImage})`
            }}
        >

            <div className="bg-white p-8 rounded shadow-md w-96">

                <h2 className="text-2xl font-bold mb-6 text-center">
                    HMS Login
                </h2>

                <input
                    type="email"
                    placeholder="Email"
                    className="w-full border p-2 mb-4 rounded"
                    onChange={(e) => setEmail(e.target.value)}
                />

                <input
                    type="password"
                    placeholder="Password"
                    className="w-full border p-2 mb-4 rounded"
                    onChange={(e) => setPassword(e.target.value)}
                />

                <button
                    onClick={handleLogin}
                    className="w-full bg-blue-500 text-white p-2 rounded"
                >
                    Login
                </button>

            </div>
        </div>
    );
}

export default LoginPage;