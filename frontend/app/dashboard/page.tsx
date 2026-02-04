const stats = [
  { label: "Calls Today", value: "12" },
  { label: "Appointments", value: "5" },
  { label: "Open Follow-ups", value: "3" }
];

export default function DashboardPage() {
  return (
    <section className="space-y-6">
      <div>
        <h1 className="text-2xl font-semibold">Dashboard</h1>
        <p className="text-sm text-slate-500">Overview of your receptionist activity.</p>
      </div>
      <div className="grid gap-4 md:grid-cols-3">
        {stats.map((stat) => (
          <div key={stat.label} className="rounded border bg-white p-4 shadow">
            <p className="text-sm text-slate-500">{stat.label}</p>
            <p className="text-2xl font-semibold">{stat.value}</p>
          </div>
        ))}
      </div>
    </section>
  );
}
