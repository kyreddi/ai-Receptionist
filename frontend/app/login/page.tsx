export default function LoginPage() {
  return (
    <section className="max-w-md space-y-4">
      <h1 className="text-2xl font-semibold">Login</h1>
      <form className="space-y-3 rounded border bg-white p-4 shadow">
        <div>
          <label className="text-sm font-medium">Email</label>
          <input className="mt-1 w-full rounded border px-3 py-2" placeholder="owner@clinic.com" />
        </div>
        <div>
          <label className="text-sm font-medium">Password</label>
          <input type="password" className="mt-1 w-full rounded border px-3 py-2" placeholder="••••••" />
        </div>
        <button className="w-full rounded bg-slate-900 px-4 py-2 text-sm text-white">Sign in</button>
      </form>
    </section>
  );
}
