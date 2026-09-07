---
name: jchatmind-source-work
description: Trace, explain, review, debug, or change the JChatMind Spring AI repository using its current source. Use for JChatMind Agent loops, tools, RAG, SSE, persistence, React integration, and project-based interview or learning tasks; do not use for generic Java or Spring questions unrelated to this checkout.
---

# JChatMind source work

Produce a source-grounded result that matches the user's requested mode and the repository's real behavior.

## Start

- Read the repository `AGENTS.md` and honor its request-mode boundary.
- Inspect the working tree, then locate the smallest relevant source set with `rg`.
- Use the current checkout as evidence. Re-check implementation details instead of relying on README claims, comments, or prior-session summaries.

For work that spans subsystems, requests a code change or review, or needs a verification plan, read [references/workflow.md](references/workflow.md). A narrow single-file explanation normally does not need the reference.

## Evidence standard

- Explain purpose first, then the actual call path, state transitions, data shape, and failure behavior.
- Cite concrete source paths and symbols. Distinguish inspected facts, executed runtime evidence, and proposals.
- Follow contracts across layers when a field, event, tool result, mapper, or API type changes.
- Do not infer production scale, privacy, performance, or retrieval quality from a successful compile.

## Interaction style

- When the user wants guidance, give one concrete edit target and one acceptance check at a time; wait for “继续” before the next edit step.
- When the user asks for implementation, complete the scoped change autonomously and run proportionate checks.
- For interview preparation, turn the verified implementation into a concise answer with the problem, design, real call path, trade-off, and honest limitation.
