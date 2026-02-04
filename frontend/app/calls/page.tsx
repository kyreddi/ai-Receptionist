const calls = [
  { id: "1", from: "+91 98765 43210", status: "IN_PROGRESS", time: "10:05 AM" },
  { id: "2", from: "+91 90000 11111", status: "ENDED", time: "09:40 AM" }
];

export default function CallsPage() {
  return (
    <section className="space-y-4">
      <h1 className="text-2xl font-semibold">Calls</h1>
      <div className="rounded border bg-white shadow">
        <table className="w-full text-sm">
          <thead className="border-b bg-slate-50 text-left">
            <tr>
              <th className="p-3">Caller</th>
              <th className="p-3">Status</th>
              <th className="p-3">Time</th>
            </tr>
          </thead>
          <tbody>
            {calls.map((call) => (
              <tr key={call.id} className="border-b last:border-0">
                <td className="p-3">{call.from}</td>
                <td className="p-3">{call.status}</td>
                <td className="p-3">{call.time}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </section>
  );
}
