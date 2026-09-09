# KnowFlow source workflow

Use this workflow for multi-file explanations, reviews, debugging, and implementation. Skip steps that do not affect the requested outcome.

## 1. Establish scope

1. Read `git status --short` and preserve unrelated changes.
2. Classify the request as explanation/review, diagnosis, or implementation.
3. Define one observable completion condition before changing code.
4. Search for the entry point and its direct producers and consumers; avoid reading an entire directory without a routing question.

## 2. Trace the relevant path

Use these routes as starting points, then verify every hop in the current checkout.

| Concern | Start here | Continue through |
| --- | --- | --- |
| Chat/Agent execution | `ChatMessageController` | `ChatEvent` → `ChatEventListener` → `AgentRuntimeFactory.create(...)` → `AgentRuntime.run()` / think-execute loop |
| Model selection | `ChatClientRegistry` | Agent configuration → factory lookup → constructed `AgentRuntime` instance |
| Tool execution | `AgentRuntime` | model tool calls → `ToolCallingManager.executeToolCalls(...)` → tool response messages → next model turn |
| Knowledge retrieval | `KnowledgeTools.knowledgeQuery(...)` | `RagServiceImpl.similaritySearch(...)` → `ChunkBgeM3Mapper` → pgvector distance → serialized tool result |
| Document ingestion | document controller/facade | Markdown parsing → chunking → embedding → `chunk_bge_m3` persistence |
| SSE status | backend `SseService` emitters | message type/payload → browser `EventSource` → `AgentChatView` state → history rendering |
| Frontend chat | `AgentChatView.handleSendMessage(...)` | `ui/src/api` → REST controller → persistence/event path → SSE refresh |

If a route disagrees with this table, update the conclusion from source; do not force the code to match the table.

## 3. Work by request type

### Explanation or review

- Do not patch unless the user separately authorizes changes.
- Reconstruct the call path in execution order and explain why each boundary exists.
- For a finding, state the trigger, the causal path, the user-visible effect, and the smallest safe correction.

### Diagnosis

- Reproduce or narrow the symptom with the cheapest relevant check.
- Distinguish code defects from missing PostgreSQL, model credentials, network access, fixture drift, or stale frontend state.
- Stop after identifying the cause unless the request also asks for a fix.

### Implementation

- Patch the smallest coherent set of producers and consumers.
- Keep public contracts backward compatible unless the request requires a contract change.
- After each meaningful milestone, run the narrowest check that can falsify the change.
- Use the verification matrix in the repository `AGENTS.md`; broaden only when new evidence warrants it.

## 4. Review the result

1. Inspect `git diff -- <task paths>` and `git diff --check`.
2. Confirm the original completion condition against the final source or runtime result.
3. Attribute failures: changed code, pre-existing baseline, or unavailable integration.
4. Report implemented behavior separately from follow-up ideas. Include exact checks run and untested boundaries.
