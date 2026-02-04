export default function HomePage() {
  return (
    <section className="space-y-4">
      <h1 className="text-3xl font-semibold">Welcome to AI Receptionist</h1>
      <p className="text-slate-600">
        Use the dashboard to manage calls, appointments, and your AI receptionist settings.
      </p>
      <a
        className="inline-flex items-center rounded bg-slate-900 px-4 py-2 text-sm text-white"
        href="/login"
      >
        Go to Login
      </a>
    </section>
  );
}
