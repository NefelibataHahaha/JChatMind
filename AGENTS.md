# KnowFlow repository guidance

## Purpose

Work from the current checkout. Treat source code and executed checks as authoritative; README claims, comments, and historical notes are context until verified.

Default to Chinese for explanations. Keep code identifiers, commands, protocol fields, and error text in their original form.

## Respect the requested mode

- For explanation, review, learning, or “先不要改” requests, inspect read-only and teach from the real call path. Do not edit files.
- For implementation, fix, or optimization requests, make the smallest cohesive change that completes the requested outcome and verify it.
- Preserve unrelated working-tree changes. Do not rewrite or clean files outside the task.
- Separate current behavior from proposed evolution. Do not describe a recommendation as implemented.

## Repository map

- `knowflow/`: Java 17, Spring Boot 3.5, Spring AI, MyBatis, PostgreSQL/pgvector backend.
- `ui/`: React 19, TypeScript, Vite, Ant Design frontend.
- `examples/`: standalone data and HTML examples; not production application code.
- `.env.example`: placeholder-only configuration contract. Real secrets belong in environment variables or ignored local configuration.

## Engineering rules

1. Inspect `git status --short` before editing and use focused `rg -n -C` searches before broad reads.
2. Trace the affected path across boundaries before changing it. Check request/response types, persistence, events, and UI consumers together when their contract changes.
3. Keep request and session state local or pass it explicitly. Spring singleton services may retain dependencies, not per-request `AgentDTO`, session IDs, prompts, or mutable task state.
4. Preserve manual tool orchestration around `internalToolExecutionEnabled(false)`. A tool-flow change must account for the prompt, model response, `ToolCallingManager`, tool result messages, and the next loop iteration.
5. Treat RAG ranking as one contract across embedding generation, pgvector SQL, mapper fields, and returned JSON. Do not call distance a similarity score without an explicit conversion.
6. Keep SSE message names aligned between backend emitters and `ui/src/types/index.ts` plus `AgentChatView.tsx`. Emit terminal success only after successful completion, not unconditionally from cleanup code.
7. Never add credentials or real personal configuration to tracked files. Do not print secret values during diagnosis.
8. Do not claim latency, concurrency, retrieval quality, database behavior, or external-model behavior without a measurement or an executed integration check.

## Verification

Choose checks proportional to the changed surface. Stop after the relevant checks pass unless a failure or unresolved risk justifies broader testing.

| Change | Required checks |
| --- | --- |
| Instructions, Skill, or Markdown only | `git diff --check`; validate changed Skills with the bundled `quick_validate.py` |
| Backend Java/XML, no runtime integration | From `knowflow/`: `./mvnw clean compile -DskipTests` (`.\mvnw.cmd` on Windows) |
| Spring wiring or application context | Backend compile, then `./mvnw -Dtest=KnowFlowApplicationTests test` |
| RAG SQL, persistence, or migrations | Backend checks plus a real PostgreSQL/pgvector integration check when the service is available |
| Frontend TypeScript/React | From `ui/`: `npm run build` and `npm run lint` |
| Cross-cutting behavior | Relevant backend and frontend checks, then the narrowest end-to-end exercise that covers the changed contract |

`AgentRuntimeV1Test` and `AgentRuntimeV2Test` call an external chat model. Run them only when credentials, network access, and paid/external execution are explicitly in scope. If a check fails outside the changed surface, report it as a baseline or environment limit instead of silently editing unrelated code.

## Completion report

Lead with the outcome. Name changed files, summarize the verified call path, list checks actually run with their results, and state any unverified runtime boundary. For reviews, give actionable findings first and avoid inventing issues when the evidence is incomplete.
