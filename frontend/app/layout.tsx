import "./globals.css";
import type { ReactNode } from "react";

export const metadata = {
  title: "AI Receptionist",
  description: "AI Receptionist SaaS"
};

export default function RootLayout({ children }: { children: ReactNode }) {
  return (
    <html lang="en">
      <body className="min-h-screen">
        <div className="mx-auto flex min-h-screen max-w-6xl flex-col px-6 py-6">
          <header className="flex items-center justify-between border-b pb-4">
            <div>
              <p className="text-xl font-semibold">AI Receptionist</p>
              <p className="text-sm text-slate-500">Admin Console</p>
            </div>
            <nav className="flex gap-4 text-sm text-slate-600">
              <a href="/dashboard" className="hover:text-slate-900">Dashboard</a>
              <a href="/calls" className="hover:text-slate-900">Calls</a>
              <a href="/appointments" className="hover:text-slate-900">Appointments</a>
              <a href="/knowledge" className="hover:text-slate-900">Knowledge Base</a>
              <a href="/simulator" className="hover:text-slate-900">Simulator</a>
              <a href="/settings" className="hover:text-slate-900">Settings</a>
            </nav>
          </header>
          <main className="flex-1 py-6">{children}</main>
        </div>
      </body>
    </html>
  );
}
