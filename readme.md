### Mermaid schematic :

[![](https://mermaid.ink/img/pako:eNqFkk9vgjAYh7_Km16GiR42twtZTBjoQrI4I_MkHgot0kxa0j_bjPjd1woal-nGqYHf-zztj-5QLghFPlpLXJfwFqUc7HO7XCgqVzAYjKBJTFYxDTO8rSjXDdwtuyUEs7jLTBgnxwRkW4ijBobLOSVMQd29rqUQhVq1huFhLvAmwtjJT6bLx0yOlMbaKJjNX8NxksTT594hhr2YayqlqfWR5tIxL4SswNitgi7x6RtYqdJss3HKnCrF-Lp3rn26rE0WodO2zuyyc061kfznmW6UPS9gCyRY0zOTxYTeVGgonK7lNqGkNnUq6-rR_ynwF_h-GRR2w5A7PhMcfIdNWuwkiF_GUfuzcu-8uA-GgX7ltHYzvSvsh7_YXW0tnHhXG7Jw1EcVlRVmxF65nZOlSJe0oiny7ZJg-Z6ilO9tztSuzDFhWkjkF3ijaB9ho0Wy5TnytTT0GIoYtte36lL7b5Wa73s)](https://mermaid.live/edit#pako:eNqFkk9vgjAYh7_Km16GiR42twtZTBjoQrI4I_MkHgot0kxa0j_bjPjd1woal-nGqYHf-zztj-5QLghFPlpLXJfwFqUc7HO7XCgqVzAYjKBJTFYxDTO8rSjXDdwtuyUEs7jLTBgnxwRkW4ijBobLOSVMQd29rqUQhVq1huFhLvAmwtjJT6bLx0yOlMbaKJjNX8NxksTT594hhr2YayqlqfWR5tIxL4SswNitgi7x6RtYqdJss3HKnCrF-Lp3rn26rE0WodO2zuyyc061kfznmW6UPS9gCyRY0zOTxYTeVGgonK7lNqGkNnUq6-rR_ynwF_h-GRR2w5A7PhMcfIdNWuwkiF_GUfuzcu-8uA-GgX7ltHYzvSvsh7_YXW0tnHhXG7Jw1EcVlRVmxF65nZOlSJe0oiny7ZJg-Z6ilO9tztSuzDFhWkjkF3ijaB9ho0Wy5TnytTT0GIoYtte36lL7b5Wa73s)

When the user submit a new payment, the API checks if a payment with same unique ID has already been submitted to the Redis<br>
If it exists and is still being processed, interrupt the payment and inform the user<br>
If it exists and is successful, returns the payment proof's ID and date

If it does not exist, the payment will be created<br>
If the creation is successful, the API returns the payment proof's ID<br>
If the creation fail, the user will be informed
