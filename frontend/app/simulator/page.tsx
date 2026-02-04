export default function SimulatorPage() {
  return (
    <section className="space-y-4">
      <h1 className="text-2xl font-semibold">AI Call Simulator</h1>
      <div className="rounded border bg-white p-4 shadow">
        <div className="mb-4 space-y-2">
          <p className="rounded bg-slate-100 p-2 text-sm">AI: Hello! How can I help you today?</p>
          <p className="rounded bg-blue-50 p-2 text-sm">Caller: I want to book a cleaning appointment.</p>
        </div>
        <div className="flex gap-2">
          <input className="flex-1 rounded border px-3 py-2 text-sm" placeholder="Type a message" />
          <button className="rounded bg-slate-900 px-4 py-2 text-sm text-white">Send</button>
        </div>
      </div>
    </section>
  );
}
