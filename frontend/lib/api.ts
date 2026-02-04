export type AuthResponse = {
  token: string;
  tenantId: string;
  userId: string;
  role: "OWNER" | "STAFF";
};

const API_BASE = process.env.NEXT_PUBLIC_API_BASE || "http://localhost:8080";

export async function login(email: string, password: string): Promise<AuthResponse> {
  const response = await fetch(`${API_BASE}/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email, password })
  });
  if (!response.ok) {
    throw new Error("Login failed");
  }
  return response.json();
}
