export default function SettingsPage() {
  return (
    <section className="space-y-4">
      <h1 className="text-2xl font-semibold">Integrations & Settings</h1>
      <div className="rounded border bg-white p-4 shadow">
        <p className="text-sm text-slate-500">Configure telephony providers and SMS settings.</p>
        <div className="mt-4 grid gap-3 md:grid-cols-2">
          <div>
            <label className="text-sm font-medium">Provider</label>
            <select className="mt-1 w-full rounded border px-3 py-2">
              <option>Twilio</option>
              <option>Exotel</option>
            </select>
          </div>
          <div>
            <label className="text-sm font-medium">Inbound Number</label>
            <input className="mt-1 w-full rounded border px-3 py-2" placeholder="+91" />
          </div>
        </div>
      </div>
    </section>
  );
}
