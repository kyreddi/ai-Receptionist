export default function KnowledgePage() {
  return (
    <section className="space-y-4">
      <h1 className="text-2xl font-semibold">Knowledge Base</h1>
      <div className="grid gap-4 md:grid-cols-2">
        <div className="rounded border bg-white p-4 shadow">
          <h2 className="font-medium">FAQs</h2>
          <p className="text-sm text-slate-500">Manage common questions and answers.</p>
          <button className="mt-3 rounded border px-3 py-1 text-sm">Add FAQ</button>
        </div>
        <div className="rounded border bg-white p-4 shadow">
          <h2 className="font-medium">Business Profile</h2>
          <p className="text-sm text-slate-500">Update hours, services, and policies.</p>
          <button className="mt-3 rounded border px-3 py-1 text-sm">Edit Profile</button>
        </div>
      </div>
    </section>
  );
}
