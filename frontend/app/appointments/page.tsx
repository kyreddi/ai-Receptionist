const appointments = [
  { id: "1", name: "Aditi", service: "Cleaning", time: "Today 3:00 PM" },
  { id: "2", name: "Rahul", service: "Whitening", time: "Tomorrow 11:00 AM" }
];

export default function AppointmentsPage() {
  return (
    <section className="space-y-4">
      <h1 className="text-2xl font-semibold">Appointments</h1>
      <div className="grid gap-4">
        {appointments.map((appointment) => (
          <div key={appointment.id} className="rounded border bg-white p-4 shadow">
            <p className="font-medium">{appointment.name}</p>
            <p className="text-sm text-slate-500">{appointment.service}</p>
            <p className="text-sm text-slate-500">{appointment.time}</p>
          </div>
        ))}
      </div>
    </section>
  );
}
